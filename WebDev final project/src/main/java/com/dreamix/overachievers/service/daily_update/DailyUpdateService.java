package com.dreamix.overachievers.service.daily_update;

import com.dreamix.overachievers.dto.daily_update.DailyUpdateDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DailyUpdateService {
    ResponseEntity<String> createDailyUpdate(DailyUpdateDto dailyUpdateDto);

    List<DailyUpdateDto> getDailyUpdatesByTeamName(String teamName);
}
