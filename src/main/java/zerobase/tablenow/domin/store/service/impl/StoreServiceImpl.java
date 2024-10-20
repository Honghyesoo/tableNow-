package zerobase.tablenow.domin.store.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zerobase.tablenow.domin.store.dto.StoreDto;
import zerobase.tablenow.domin.store.entity.Stores;
import zerobase.tablenow.domin.store.repository.StoreRepository;
import zerobase.tablenow.domin.store.service.StoreService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;

    @Override
    public List<StoreDto> list() {
        return storeRepository.findAll().stream()
                .map(store -> StoreDto.builder()
                        .storeName(store.getStoreName())
                        .storeLocation(store.getStoreLocation())
                        .storeImg(store.getStoreImg())
                        .storeContents(store.getStoreContents())
                        .storeOpen(store.getStoreOpen())
                        .storeClose(store.getStoreClose())
                        .storeWeekOff(store.getStoreWeekOff())
                        .build())
                .collect(Collectors.toList());
    }

}
