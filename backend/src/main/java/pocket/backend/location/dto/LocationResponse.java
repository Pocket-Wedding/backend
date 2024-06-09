package pocket.backend.location.dto;

import lombok.Getter;
import pocket.backend.location.domain.Location;

import java.util.List;

@Getter
public class LocationResponse {

    String name;

    Long totalCount;

    public LocationResponse(String name, Long totalCount) {
        this.name = name;
        this.totalCount = totalCount;
    }

    public static List<LocationResponse> toList(List<Location> locationList){
        return locationList.stream()
                .map(LocationResponse::of)
                .toList();
    }

    private static LocationResponse of(Location location){
        return new LocationResponse(
                location.getName(),
                location.getTotalCount()
        );
    }

}
