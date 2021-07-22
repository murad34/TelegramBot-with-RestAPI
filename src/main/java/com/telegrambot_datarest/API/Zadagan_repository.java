package com.telegrambot_datarest.API;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface Zadagan_repository extends JpaRepository<Zadagan_entity,Integer> {}
