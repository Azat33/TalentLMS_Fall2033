package com.talentLMS.API.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Certifications {
    @JsonProperty("course_id")
    String courseId;
    @JsonProperty("course_name")
    String courseName;
    @JsonProperty("unique_id")
    String uniqueId;
    @JsonProperty("issued_date")
    String issuedDate;
    @JsonProperty("issued_date_timestamp")
    String issuedDateTimestamp;
    @JsonProperty("expiration_date")
    String expirationDate;
    @JsonProperty("expiration_date_timestamp")
    String expirationDateTimestamp;
    @JsonProperty("download_url")
    String downloadUrl;
    @JsonProperty("public_url")
    String publicUrl;




}
