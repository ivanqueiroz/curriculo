package com.ivanqueiroz.curriculo.repository;

import com.ivanqueiroz.curriculo.model.Curriculo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Ivan Queiroz <ivanqueiroz@gmail.com>
 */
public interface CurriculoRepository extends JpaRepository<Curriculo, Long> {

}
