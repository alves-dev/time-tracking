package br.com.igorma.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
@MappedSuperclass
public abstract class BaseEntity extends PanacheEntityBase {

    @Id
    private UUID id;

    BaseEntity() {
        this.id = UUID.randomUUID();
    }
}
