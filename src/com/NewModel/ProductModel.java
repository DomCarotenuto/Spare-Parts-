package com.NewModel;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import com.NewBean.ProductBean;

public interface ProductModel {
	
	public void doSave(ProductBean product) throws SQLException;

	public boolean doDelete(int code) throws SQLException;

	public ProductBean doRetrieveByKey(int code) throws SQLException;
	
	public Collection<ProductBean> doRetrieveAll(String order) throws SQLException;
	
	public void doUpdate(ProductBean product) throws SQLException;
	
	public List<ProductBean> doRetrieveByNomeOrDescrizione(String against);
	
	public Collection<ProductBean> doRetrieveByCategoria(String categoria) throws SQLException;
}
