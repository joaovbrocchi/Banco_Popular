package com.joaovbrocchi.bancopopular.repository;



import com.joaovbrocchi.bancopopular.domain.pix.ChavePix;
import com.joaovbrocchi.bancopopular.domain.pix.ParametrosDeConsulta;
import com.joaovbrocchi.bancopopular.repository.CustomPixRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.Optional;

public class CustomPixRepositoryImpl implements CustomPixRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<ChavePix> findByParameterAndValue(ParametrosDeConsulta parameter, Object value) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ChavePix> query = cb.createQuery(ChavePix.class);
        Root<ChavePix> root = query.from(ChavePix.class);

        Predicate predicate = cb.equal(root.get(parameter.name()), value);
        query.select(root).where(predicate);

        return entityManager.createQuery(query).getResultList().stream().findFirst();
    }
}

