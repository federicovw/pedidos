package com.federicovw.pedidos.service.impl;

import com.federicovw.pedidos.daos.OpinionRepository;
import com.federicovw.pedidos.model.Opinion;
import com.federicovw.pedidos.service.OpinionService;
import com.federicovw.pedidos.util.OpinionFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OpinionServiceImpl implements OpinionService {

    @Autowired
    OpinionRepository opinionRepository;

    /**
     * Persists the received opinion
     * Sets the createDate and lastUpdate a the time of creation
     * @param opinion
     * @return Saved opinion
     */
    @Override
    public Opinion create(Opinion opinion) {
        if (opinion.getId() != null) {
            throw new IllegalArgumentException("Id cannot be assigned when creating an Opinion");
        }
        validateOpinion(opinion);
        LocalDateTime now = LocalDateTime.now();
        opinion.setCreateDate(now);
        opinion.setLastUpdate(now);
        return opinionRepository.save(opinion);
    }

    /**
     * Validates the given Opinion before save or update
     * @param opinion
     */
    private void validateOpinion(Opinion opinion) {
        String msg = null;
        if (opinion == null) {
            msg = "An opinion is needed to perform the save";
        } else if (opinion.isDeleted()) {
            msg = "Cannot create a deleted Opinion";
        } else {
            List<String> fields = new ArrayList<>();
            if (opinion.getUserId() <= 0) {
                fields.add("User ID");
            }
            if (opinion.getShopId() <= 0) {
                fields.add("Shop ID");
            }
            if (opinion.getPurchaseId() <= 0) {
                fields.add("Purchase ID");
            }
            if (opinion.getScore() <= 0 || opinion.getScore() > 5) {
                fields.add("Score");
            }

            if (!fields.isEmpty()) {
                msg = "The following fields are not populated or invalid: " + fields;
            }
        }


        if (msg != null) {
            throw new IllegalArgumentException(msg);
        }
    }

    /**
     * Logically deletes an Opinions with the given ID
     * @param opinionId
     */
    @Override
    public void delete(Long opinionId) {
        Opinion opinion = opinionRepository.findById(opinionId).orElse(null);
        if (opinion == null || opinion.isDeleted()) {
            throw new IllegalArgumentException("The opinion you're trying to delete doesn't exist");
        }
        opinionRepository.deleteById(opinionId);
    }

    /**
     * Returns a single opinion matching the given purchaseId
     * @param purchaseId
     * @return
     */
    @Override
    public Opinion getOpinionByPurchase(Long purchaseId) {
        return opinionRepository.findOpinionByPurchase(purchaseId);
    }

    /**
     * Receives an OpinionFilter with the values to be used to filter the search
     * Returns a list of the Opinions matching the filters
     * Logically deleted opinions are not to be returned by requirement.
     * @param opinionFilter
     * @return
     */
    @Override
    public List<Opinion> getOpinions(OpinionFilter opinionFilter) {
        if (opinionFilter.getDeleted() != null && opinionFilter.getDeleted()) {
            throw new IllegalArgumentException("Cannot request for deleted opinions");
        }
        return opinionRepository.findOpinionsBy(opinionFilter);
    }
}
