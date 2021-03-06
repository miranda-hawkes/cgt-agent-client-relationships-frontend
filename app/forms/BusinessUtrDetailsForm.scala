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

package forms

import javax.inject.Inject

import common.FormValidation
import models.BusinessUtrDetailsModel
import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.{I18nSupport, Messages, MessagesApi}

class BusinessUtrDetailsForm @Inject()(val messagesApi: MessagesApi) extends I18nSupport {
  val businessUtrDetailsForm: Form[BusinessUtrDetailsModel] = Form(
    mapping(
      "registeredName" -> text
        .verifying(Messages("businessDetails.error"), FormValidation.nonEmptyCheck),
      "utr" -> text
        .verifying(Messages("businessDetails.error"), FormValidation.nonEmptyCheck)
    )(BusinessUtrDetailsModel.apply)(BusinessUtrDetailsModel.unapply)
  )
}
