package com.financaswhatsapp.repository;

import com.financaswhatsapp.entity.Assinatura;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AssinaturaRepository extends JpaRepository <Assinatura, UUID> {
}
