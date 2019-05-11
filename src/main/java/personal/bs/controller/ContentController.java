//package personal.bs.controller;
//
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import personal.bs.domain.vo.PageResult;
//import personal.bs.po.TbContent;
//
//import javax.annotation.Resource;
//import java.util.List;
//
///**
// * controller 广告
// * @author Administrator
// *
// */
//@RestController
//@RequestMapping("/content")
//public class ContentController {
//
//	@Resource
//	private ContentService contentService;
//
//	/**
//	 * 返回全部列表
//	 * @return
//	 */
//	@RequestMapping("/findAll")
//	public List<TbContent> findAll(){
//		return contentService.findAll();
//	}
//
//
//	/**
//	 * 返回全部列表
//	 * @return
//	 */
//	@RequestMapping("/findPage")
//	public PageResult findPage(int page, int rows){
//		return contentService.findPage(page, rows);
//	}
//
//	/**
//	 * 增加
//	 * @param content
//	 * @return
//	 */
//	@RequestMapping("/add")
//	public Result add(@RequestBody TbContent content){
//		try {
//			contentService.add(content);
//			return new Result(true, "增加成功");
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new Result(false, "增加失败");
//		}
//	}
//
//	/**
//	 * 修改
//	 * @param content
//	 * @return
//	 */
//	@RequestMapping("/update")
//	public Result update(@RequestBody TbContent content){
//		try {
//			contentService.update(content);
//			return new Result(true, "修改成功");
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new Result(false, "修改失败");
//		}
//	}
//
//	/**
//	 * 获取实体
//	 * @param id
//	 * @return
//	 */
//	@RequestMapping("/findOne")
//	public TbContent findOne(Integer id){
//		return contentService.findOne(id);
//	}
//
//	/**
//	 * 批量删除
//	 * @param ids
//	 * @return
//	 */
//	@RequestMapping("/delete")
//	public Result delete(Integer [] ids){
//		try {
//			contentService.delete(ids);
//			return new Result(true, "删除成功");
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new Result(false, "删除失败");
//		}
//	}
//
//		/**
//	 * 查询+分页
//
//	 * @param page
//	 * @param rows
//	 * @return
//	 */
//	@RequestMapping("/search")
//	public PageResult search(@RequestBody TbContent content, int page, int rows  ){
//		return contentService.findPage(content, page, rows);
//	}
//
//}
