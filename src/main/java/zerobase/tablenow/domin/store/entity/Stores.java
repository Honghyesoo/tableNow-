package zerobase.tablenow.domin.store.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import zerobase.tablenow.domin.member.entity.Users;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stores")
public class Stores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;
    private String storeName;
    private String storeLocation;
    private String storeImg;
    private String storeContents;
    private String storeOpen;
    private String storeClose;
    private String storeWeekOff;
}
