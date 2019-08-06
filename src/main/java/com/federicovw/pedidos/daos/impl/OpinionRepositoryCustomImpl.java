package com.federicovw.pedidos.daos.impl;

import com.federicovw.pedidos.daos.OpinionRepositoryCustom;
import com.federicovw.pedidos.model.Opinion;
import com.federicovw.pedidos.util.OpinionFilter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class OpinionRepositoryCustomImpl implements OpinionRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Opinion> findOpinionsBy(OpinionFilter opinionFilter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Opinion> query = cb.createQuery(Opinion.class);
        Root<Opinion> opinionRoot = query.from(Opinion.class);
        List<Predicate> predicates = getPredicatesFromFilter(cb, opinionRoot, opinionFilter);
        query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        return entityManager.createQuery(query)
                .getResultList();
    }

    private List<Predicate> getPredicatesFromFilter(CriteriaBuilder cb, Root<Opinion> opinionRoot, OpinionFilter opinionFilter) {
        List<Predicate> predicates = new ArrayList<>();
        if (opinionFilter.getId() != null){
            predicates.add(cb.equal(opinionRoot.get("id"), opinionFilter.getId()));
        }
        if (opinionFilter.getCreateDateFrom() != null){
            predicates.add(cb.greaterThanOrEqualTo(opinionRoot.get("createDate"), opinionFilter.getCreateDateFrom()));
        }
        if (opinionFilter.getCreateDateTo() != null){
            predicates.add(cb.lessThanOrEqualTo(opinionRoot.get("createDate"), opinionFilter.getCreateDateTo()));
        }
        if (opinionFilter.getLastUpdateFrom() != null){
            predicates.add(cb.greaterThanOrEqualTo(opinionRoot.get("lastUpdate"), opinionFilter.getLastUpdateFrom()));
        }
        if (opinionFilter.getLastUpdateTo() != null){
            predicates.add(cb.lessThanOrEqualTo(opinionRoot.get("lastUpdate"), opinionFilter.getLastUpdateTo()));
        }
        if (opinionFilter.getUserId() != null){
            predicates.add(cb.equal(opinionRoot.get("userId"), opinionFilter.getUserId()));
        }
        if (opinionFilter.getShopId() != null){
            predicates.add(cb.equal(opinionRoot.get("shopId"), opinionFilter.getShopId()));
        }
        if (opinionFilter.getPurchaseId() != null){
            predicates.add(cb.equal(opinionRoot.get("purchaseId"), opinionFilter.getPurchaseId()));
        }
        if (opinionFilter.getComment() != null){
            predicates.add(cb.like(opinionRoot.get("comment"), opinionFilter.getComment()));
        }
        if (opinionFilter.getScore() != null){
            predicates.add(cb.equal(opinionRoot.get("score"), opinionFilter.getScore()));
        }
        if (opinionFilter.getDeleted() != null){
            predicates.add(cb.equal(opinionRoot.get("deleted"), opinionFilter.getDeleted()));
        }
        return predicates;
    }
}
