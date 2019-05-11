package personal.bs.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import personal.bs.service.ItemSearchService;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/itemsearch")
public class ItemSearchController {
	
	@Resource
	private ItemSearchService itemSearchService;
	
	@RequestMapping("/search")
	public Map search(@RequestBody Map searchMap){
		
		return itemSearchService.search(searchMap);
		
	}

}
