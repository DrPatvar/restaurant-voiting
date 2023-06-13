package com.github.drpatvar.repository;

import org.springframework.transaction.annotation.Transactional;
import com.github.drpatvar.model.Menu;

@Transactional(readOnly = true)
public interface MenuRepository extends BaseRepository<Menu> {
}
