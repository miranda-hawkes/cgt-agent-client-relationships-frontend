/*
 * Copyright 2017 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package predicates

import java.net.URI
import javax.inject.Inject

import checks.AffinityGroupCheck
import play.api.mvc.Results._
import play.api.mvc.{AnyContent, Request}
import uk.gov.hmrc.play.frontend.auth.{AuthContext, PageBlocked, PageIsVisible, PageVisibilityResult, _}
import uk.gov.hmrc.play.http.HeaderCarrier
import services.AuthorisationService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class AffinityGroupAgentPredicate @Inject()(authorisationService: AuthorisationService)(errorPageUri: URI)
  extends PageVisibilityPredicate {

  private val errorPage = Future.successful(Redirect(errorPageUri.toString))

  private def pageVisibility(check: Boolean): PageVisibilityResult = {
    if (check) PageIsVisible
    else PageBlocked(errorPage)
  }

  private def affinityGroupSupplied(group: String): Future[PageVisibilityResult] = {
    val check = AffinityGroupCheck.affinityGroupCheckAgent(group)
    for {
      affinityGroupCheck <- check
    } yield pageVisibility(affinityGroupCheck)
  }

  private def affinityGroupRouting(group: Option[String]): Future[PageVisibilityResult] = group match {
    case Some(data) => affinityGroupSupplied(data)
    case _ => Future.successful(PageBlocked(errorPage))
  }

  override def apply(authContext: AuthContext, request: Request[AnyContent]): Future[PageVisibilityResult] = {

    implicit def hc(implicit request: Request[_]): HeaderCarrier = HeaderCarrier.fromHeadersAndSession(request.headers, Some(request.session))

    val authorityAffinityGroup: Future[Option[String]] = authorisationService.getAffinityGroup(hc(request))

    for {
      affinityGroup <- authorityAffinityGroup
      visibility <- affinityGroupRouting(affinityGroup)
    } yield visibility
  }

}