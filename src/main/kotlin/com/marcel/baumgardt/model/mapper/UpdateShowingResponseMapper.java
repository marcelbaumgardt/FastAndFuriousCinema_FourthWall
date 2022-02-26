package com.marcel.baumgardt.model.mapper;

import com.marcel.baumgardt.model.dto.UpdateShowingResponse;
import com.marcel.baumgardt.model.dto.UpdateShowingResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.marcel.baumgardt.model.dto.UpdateShowingResponseStatus.NO_ENTITIES_AFFECTED;
import static com.marcel.baumgardt.model.dto.UpdateShowingResponseStatus.UPDATED;

@Component
@RequiredArgsConstructor
public class UpdateShowingResponseMapper {
    public UpdateShowingResponse mapToUpdateShowingResponse(int count) {
        return count == 0
                ? getUpdateShowingResponse(count, NO_ENTITIES_AFFECTED)
                : getUpdateShowingResponse(count, UPDATED);
    }

    private UpdateShowingResponse getUpdateShowingResponse(int count, UpdateShowingResponseStatus status) {
        return UpdateShowingResponse.builder()
                .status(status)
                .affectedEntities(count)
                .build();
    }
}
