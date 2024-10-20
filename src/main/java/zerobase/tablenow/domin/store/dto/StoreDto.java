package zerobase.tablenow.domin.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreDto {
    private String storeName;
    private String storeLocation;
    private String storeImg;
    private String storeContents;
    private String storeOpen;
    private String storeClose;
    private String storeWeekOff;
}
