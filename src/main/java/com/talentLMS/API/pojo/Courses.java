package com.talentLMS.API.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Courses {
    String id;
    String name;
    String role;
    String code;
    @JsonProperty("category_id")
    String categoryId;
    String description;
    String price;
    String status;
    @JsonProperty("creation_date")
    String creationDate;
    @JsonProperty("last_update_on")
    String lastUpdateOn;
    @JsonProperty("creator_id")
    String creatorId;
    @JsonProperty("hide_from_catalog")
    String hideFromCatalog;
    @JsonProperty("time_limit")
    String timeLimit;
    @JsonProperty("start_datetime")
    String startDatetime;
    @JsonProperty("expiration_datetime")
    String expirationDatetime;
    String level;
    String shared;
    @JsonProperty("shared_url")
    String sharedUrl;
    String avatar;
    @JsonProperty("big_avatar")
    String bigAvatar;
    String certification;
    @JsonProperty("certification_duration")
    String certificationDuration;
    @JsonProperty("enrolled_on")
    String enrolledOn;
    @JsonProperty("enrolled_on_timestamp")
    String enrolledOnTimestamp;
    @JsonProperty("completed_on")
    String completedOn;
    @JsonProperty("completed_on_timestamp")
    String completedOnTimestamp;
    @JsonProperty("completion_status")
    String completionStatus;
    @JsonProperty("completion_status_formatted")
    String completionStatusFormatted;
    @JsonProperty("completion_percentage")
    String completionPercentage;
    @JsonProperty("expired_on")
    String expiredOn;
    @JsonProperty("expired_on_timestamp")
    String expiredOnTimestamp;
    @JsonProperty("total_time")
    String totalTime;
    @JsonProperty("total_time_seconds")
    String totalTimeSeconds;
    @JsonProperty("last_accessed_unit_url")
    String lastAccessedUnitUrl;
}
