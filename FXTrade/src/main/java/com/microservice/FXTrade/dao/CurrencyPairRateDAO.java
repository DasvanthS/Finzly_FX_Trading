package com.microservice.FXTrade.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.microservice.FXTrade.model.CurrencyPairRate;

@Repository
public class CurrencyPairRateDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Optional<CurrencyPairRate> findByCurrencyPair(String currenyPair) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(CurrencyPairRate.class);
		criteria.add(Restrictions.eq("currencyPair", currenyPair));
		CurrencyPairRate currency = (CurrencyPairRate) criteria.uniqueResult();
		session.close();
		return Optional.ofNullable(currency);
	}
	
	public void save(CurrencyPairRate currencyPairRate) {
		Session session = sessionFactory.openSession();
		session.save(currencyPairRate);
		session.beginTransaction().commit();	
		session.close();
	}
	
	public void updateRate(String currenyPair, Double rate) {
		Session session = sessionFactory.openSession();
		CurrencyPairRate currencyPairRate = findByCurrencyPair(currenyPair).get();
		currencyPairRate.setRate(rate);;
		session.update(currencyPairRate);
		session.beginTransaction().commit();
		session.close();
	}
	
	public void deleteCurrencyPair(CurrencyPairRate currencyPairRate) {
		Session session = sessionFactory.openSession();
        CurrencyPairRate attachedEntity = (CurrencyPairRate) session.merge(currencyPairRate);
		session.delete(attachedEntity);
		session.beginTransaction().commit();
		session.close();
	}
	
	public List<CurrencyPairRate> getAll(){
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(CurrencyPairRate.class);
		return criteria.list();
	}

}
