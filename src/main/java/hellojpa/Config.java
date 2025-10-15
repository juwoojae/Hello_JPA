package hellojpa;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Config {
    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
}
