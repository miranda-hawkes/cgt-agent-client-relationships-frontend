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

package views

import data.MessageLookup.CorrespondenceDetails
import forms.CorrespondenceDetailsForm
import org.jsoup.Jsoup
import traits.ViewSpecHelper
import views.html.correspondenceDetails

class CorrespondenceDetailsViewSpec extends ViewSpecHelper {

  "The confirm permission view with a form with no errors" should {
    lazy val form = new CorrespondenceDetailsForm(messagesApi)
    lazy val view = correspondenceDetails(config, form.correspondenceDetailsForm)
    lazy val doc = Jsoup.parse(view.body)

    "have a header" which {
      lazy val header = doc.select("h1")

      "has a class of heading-xlarge" in {
        header.attr("class") shouldBe "heading-xlarge"
      }

      s"has the text ${CorrespondenceDetails.title}" in {
        header.text() shouldBe CorrespondenceDetails.title
      }
    }
  }
}
