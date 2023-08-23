package com.jjuarez.gila.request;


import com.jjuarez.gila.constants.ApiConstants;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record NotificationRequest(
        @NotEmpty(message = "Category must not be empty")
        @Pattern(regexp = ApiConstants.CATEGORY_REGEX_PATTERN,message = "Category should one of: "
                + ApiConstants.CATEGORY_REGEX_PATTERN)
        String category,

        @NotEmpty(message = "Message must not be empty")
        @Size(min = 3, max = 110, message = "Message can have a maximum of 150 characters")
        @Pattern(regexp = "\\p{ASCII}*", message = "Message should only contain ASCII characters")
        String message
) {
}
