package com.federicovw.pedidos.daos;

import com.federicovw.pedidos.model.Opinion;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@Repository
public interface OpinionRepository extends JpaRepository<Opinion, Long>, JpaSpecificationExecutor<Opinion>, OpinionRepositoryCustom {

    @Override
    @Modifying
    @Query("update Opinion o set o.deleted = true where o.id = id")
    void deleteById(Long id);

    @Query("SELECT o FROM Opinion o WHERE o.purchaseId = :purchaseId AND o.deleted = false")
    Opinion findOpinionByPurchase(@Param("purchaseId") Long purchaseId);


}
