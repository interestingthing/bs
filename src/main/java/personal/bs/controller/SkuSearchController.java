package personal.bs.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import personal.bs.service.SkuSearchService;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/itemsearch")
public class SkuSearchController {
	
	@Resource
	private SkuSearchService itemSearchService;
	
	@RequestMapping("/search")
	public Map search(@RequestBody Map searchMap){
		
		return itemSearchService.search(searchMap);
		
	}

}
