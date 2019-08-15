/*
     Copyright 2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package de.markthome.wheelmap.utils;

import java.util.Locale;
import java.util.ResourceBundle;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;

public class SkillUtils {

    public static ResourceBundle getResourceBundle(HandlerInput handlerInput, String bundleName) {
        final Locale locale = new Locale(handlerInput.getRequestEnvelope().getRequest().getLocale());
        return ResourceBundle.getBundle("Messages", locale);
    }
}
