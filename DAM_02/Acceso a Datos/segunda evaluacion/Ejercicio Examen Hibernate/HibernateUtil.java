package dam02.accesoadatos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static Session session;

    public static void buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure(HibernateUtil.class.getResource("hibernate.cfg.xml"));
            configuration.addAnnotatedClass(Granjero.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            System.err.println("Error al crear la sesión de fábrica de Hibernate: " + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static void openSession() {
        session = sessionFactory.openSession();
    }

    public static Session getCurrentSession() {

        if ((session == null) || (!session.isOpen()))
            openSession();

        return session;
    }

    public static void closeSessionFactory() {

        if (session != null)
            session.close();

        if (sessionFactory != null)
            sessionFactory.close();
    }
}
