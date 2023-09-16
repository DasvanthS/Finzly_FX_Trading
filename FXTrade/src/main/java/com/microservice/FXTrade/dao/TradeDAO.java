package com.microservice.FXTrade.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.microservice.FXTrade.model.Trade;

@Repository
public class TradeDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void saveTrade(Trade trade) {
		Session session = sessionFactory.openSession();
		session.save(trade);
		session.beginTransaction().commit();	
		session.close();
	}
	
	public List<Trade> getAll(){
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Trade.class);
		return criteria.list();
	}

}
