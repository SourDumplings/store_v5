package cn.itcast.store.service.serviceImpl;

import java.util.List;

import cn.itcast.store.dao.ProductDao;
import cn.itcast.store.domain.PageModel;
import cn.itcast.store.domain.Product;
import cn.itcast.store.service.ProductService;
import cn.itcast.store.utils.BeanFactory;

public class ProductServiceImpl implements ProductService
{
	private ProductDao productDao = (ProductDao) BeanFactory.createObject("ProductDao");
	
	public List<Product> findNews() throws Exception
	{
		// TODO Auto-generated method stub
		return productDao.findNews();
	}

	public List<Product> findHots() throws Exception
	{
		// TODO Auto-generated method stub
		return productDao.findHots();
	}

	public Product findProductByPid(String pid) throws Exception
	{
		// TODO Auto-generated method stub
		return productDao.findProductByPid(pid);
	}

	public PageModel findProductsByCidWithPage(String cid, int curNum) throws Exception
	{
		// TODO Auto-generated method stub
		// 1.创建PageModel对象：计算分页参数
		int totalRecords = productDao.findTotalRecords(cid);
		PageModel pageModel = new PageModel(curNum, 5, totalRecords);
		// 2.关联集合
		List list = productDao.findProductsByCidWithPage(cid, pageModel.getCurrentPageNum(), pageModel.getPageSize());
		pageModel.setList(list);
		// 3.关联URL
		pageModel.setUrl("ProductServlet?method=findProductsByCidWithPage&cid=" + cid);
		return pageModel;
	}

	public PageModel findAllProductsWithPage(int curNum) throws Exception
	{
		// TODO Auto-generated method stub
		// 1.创建PageModel对象：计算分页参数
		int totalRecords = productDao.findTotalRecords();
		PageModel pageModel = new PageModel(curNum, 5, totalRecords);
		// 2.关联集合
		List<Product> list = productDao.findAllProductsWithPage(pageModel.getCurrentPageNum(), pageModel.getPageSize());
		pageModel.setList(list);
		// 3.关联URL
		pageModel.setUrl("AdminProductServlet?method=findAllProductsWithPage");
		return pageModel;
	}

	public void saveProduct(Product product) throws Exception
	{
		// TODO Auto-generated method stub
		productDao.saveProduct(product);
		
	}
	
	

}
