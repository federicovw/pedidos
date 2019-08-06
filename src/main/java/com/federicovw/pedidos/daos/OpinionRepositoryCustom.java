package com.federicovw.pedidos.daos;

import com.federicovw.pedidos.model.Opinion;
import com.federicovw.pedidos.util.OpinionFilter;

import java.util.List;

public interface OpinionRepositoryCustom {
    List<Opinion> findOpinionsBy(OpinionFilter opinionFilter);
}
