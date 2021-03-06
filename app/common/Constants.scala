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

package common

object Constants {

  object AffinityGroup {
    val agent = "Agent"
    val individual = "Individual"
    val organisation = "Organisation"
  }

  object Audit {
    val splunk: String = "SPLUNK AUDIT:\n"
    val transactionGetClientList: String = "CGT Government Gateway Get Client List"
    val transactionSubmitClientDetails: String = "CGT Agent Registering Client Details"
    val eventTypeFailure: String = "CGTFailure"
    val eventTypeSuccess: String = "CGTSuccess"
  }

  object ClientType {
    val individual = "Individual"
    val company = "Company"
  }

  object BusinessType {
    val nonUK = "NUK"
    val limitedCompany = "LTD"
  }
}
