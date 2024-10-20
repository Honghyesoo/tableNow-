package zerobase.tablenow.domin.store.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import zerobase.tablenow.domin.store.dto.StoreDto;
import zerobase.tablenow.domin.store.service.StoreService;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @GetMapping("store/list")
    public String store(Model model) {
        List<StoreDto> stores = storeService.list();
        model.addAttribute("stores",stores);

        return "order/list";
    }

}
