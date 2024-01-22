package com.dreamix.overachievers.mapper;

import com.dreamix.overachievers.dto.employee.EmployeeDto;
import com.dreamix.overachievers.dto.review.ReviewDto;
import com.dreamix.overachievers.dto.review.ReviewInfoDto;
import com.dreamix.overachievers.entity.Employee;
import com.dreamix.overachievers.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
@Component
public interface ReviewMapper {
    List<ReviewDto> reviewEntityToReviewDto(List<Review> src);

    @Mapping(source = "sender", target = "sender", qualifiedByName = "employeeToEmployeeDto")
    @Mapping(source = "receiver", target = "receiver", qualifiedByName = "employeeToEmployeeDto")
    ReviewDto reviewEntityToReviewDto(Review src);

    List<Review> reviewDtoToReviewEntity(List<ReviewDto> src);

    Review reviewDtoToReviewEntity(ReviewDto src);

    @Named("employeeToEmployeeDto")
    default EmployeeDto employeeToEmployeeDto(Employee employee) {
        return MapperHelper.toEmployeeDto(employee);
    }

    @Mapping(source = "sender", target = "sender", qualifiedByName = "employeeToEmployeeDto")
    ReviewInfoDto reviewEntityToReviewForEmployeeDto(Review src);

    List<ReviewInfoDto> reviewEntityToReviewInfoDto(List<Review> src);
}