package cn.itcast.store.service;

import java.util.List;

import cn.itcast.store.domain.Product;
import cn.itcast.store.utils.PageModel;

public interface ProductService
{

	List<Product> findNews() throws Exception;

	List<Product> findHots() throws Exception;

	Product findProductByPid(String pid) throws Exception;

	PageModel findProductsByCidWithPage(String cid, int curNum) throws Exception;

}
