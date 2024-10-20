package zerobase.tablenow.domin.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zerobase.tablenow.domin.store.entity.Stores;

public interface StoreRepository extends JpaRepository<Stores,Long> {
}
