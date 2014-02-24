/**
 * 
 */
package com.javaxyq.data.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;


import com.javaxyq.data.Items;
import com.javaxyq.data.ItemsDAO;
import com.javaxyq.data.MedicineItemException;
import com.javaxyq.data.NonexistentEntityException;
import com.javaxyq.data.PreexistingEntityException;
import com.javaxyq.data.WeaponItem;
import com.javaxyq.util.DBToolkit;
import com.javaxyq.util.SmartBeanProcessor;

/**
 * ҩƷ��ȡʵ����
 * @author gongdewei
 * @date 2011-4-30 create
 */
public class WeaponItemDAOImpl implements ItemsDAO {

	private BeanListHandler<WeaponItem> resultHandler;

	public WeaponItemDAOImpl() {
		BasicRowProcessor rowProcessor = new BasicRowProcessor(new SmartBeanProcessor());
		resultHandler = new BeanListHandler<WeaponItem>(WeaponItem.class, rowProcessor);
	}
	
	@Override
	public void create(Items item) throws PreexistingEntityException, MedicineItemException {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy(Long id) throws NonexistentEntityException,MedicineItemException {
		// TODO Auto-generated method stub

	}

	@Override
	public void edit(Items weaponitem) throws NonexistentEntityException, MedicineItemException {
		// TODO Auto-generated method stub

	}

	@Override
	public Items findItem(Long id) throws MedicineItemException {
		String sql = "select * from ITEM_WEAPON where id=? ";
		List<WeaponItem> results;
		try {
			QueryRunner runner = new QueryRunner(DBToolkit.getDataSource());
			results = runner.query(sql, resultHandler, id);
		} catch (Exception ex) {
			throw new MedicineItemException(ex);
		}
		if(results.size() == 1) {
			return results.get(0);
		}else if(results.size() >= 1) {
			throw new MedicineItemException("��¼��Ψһ");
		}
		return null;
	}

	@Override
	public Items findItemByName(String name) throws MedicineItemException {
		String sql = "select * from ITEM_WEAPON where name=? ";
		List<WeaponItem> results;
		try {
			QueryRunner runner = new QueryRunner(DBToolkit.getDataSource());
			results = runner.query(sql, resultHandler, name);
		} catch (Exception ex) {
			throw new MedicineItemException(ex);
		}
		if(results.size() == 1) {
			return results.get(0);
		}else if(results.size() >= 1) {
			throw new MedicineItemException("��¼��Ψһ");
		}
		return null;
	}


	public List<WeaponItem> findItemEntities() throws MedicineItemException {
		String sql = "select * from ITEM_WEAPON ";
		try {
			QueryRunner runner = new QueryRunner(DBToolkit.getDataSource());
			List<WeaponItem> results = runner.query(sql, resultHandler);
			return results;
		} catch (Exception e) {
			throw new MedicineItemException(e);
		}
	}

	
	public List<WeaponItem> findItemEntities(int maxResults, int firstResult) throws MedicineItemException {
		if(firstResult < 1) {
			firstResult = 1;
		}
		if(maxResults < 0) {
			maxResults = 0;
		}
		String sql = "select * from ITEM_WEAPON OFFSET ? ROWS FETCH NEXT ? ROWS";
		try {
			QueryRunner runner = new QueryRunner(DBToolkit.getDataSource());
			List<WeaponItem> results = runner.query(sql, resultHandler, firstResult, maxResults);
			return results;
		} catch (SQLException e) {
			throw new MedicineItemException(e);
		}
	}

	
	public List<WeaponItem> findItemsByType(int type) throws MedicineItemException {
		String sql = "select * from ITEM_WEAPON where type=? ";
		try {
			QueryRunner runner = new QueryRunner(DBToolkit.getDataSource());
			List<WeaponItem> results = runner.query(sql, resultHandler, Integer.toHexString(type));
			return results;
		} catch (Exception e) {
			throw new MedicineItemException(e);
		}
	}

	@Override
	public String findTypeByName(String name) throws MedicineItemException {
		String sql = "select * from ITEM_WEAPON where name=? ";
		List<WeaponItem> results;
		try {
			QueryRunner runner = new QueryRunner(DBToolkit.getDataSource());
			results = runner.query(sql, resultHandler, name);
		} catch (Exception ex) {
			throw new MedicineItemException(ex);
		}
		if(results.size() == 1) {
			WeaponItem item = results.get(0);
			return item.getType();
		}else if(results.size() >= 1) {
			throw new MedicineItemException("��¼��Ψһ");
		}
		return null;
	}

	@Override
	public int getItemCount() throws MedicineItemException {
		// TODO Auto-generated method stub
		return 0;
	}
	


}