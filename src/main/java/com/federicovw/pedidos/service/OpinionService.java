package com.federicovw.pedidos.service;

import com.federicovw.pedidos.model.Opinion;
import com.federicovw.pedidos.util.OpinionFilter;

import java.util.List;

public interface OpinionService {

    Opinion create(Opinion opinion);

    void delete(Long opinionId);

    Opinion getOpinionByPurchase(Long purchaseId);

    List<Opinion> getOpinions(OpinionFilter opinionFilter);

}
