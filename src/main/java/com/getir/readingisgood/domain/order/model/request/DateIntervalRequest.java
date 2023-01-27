package com.getir.readingisgood.domain.order.model.request;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class DateIntervalRequest {

	@NonNull
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	Date startDate;

	@NonNull
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	Date endDate;
}
