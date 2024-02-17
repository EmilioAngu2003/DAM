package dam02.accesoadatos;

import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

public class GranjerosService {

    public Granjero getGranjero(int id) {
        try (Session session = HibernateUtil.getCurrentSession()) {
            session.beginTransaction();
            Granjero granjero = session.get(Granjero.class, id);
            session.getTransaction().commit();
            return granjero;
        } catch (Exception e) {
            System.out.println("Error en Granjero getGranjero(int id) | " + e.getMessage());
            return null;
        }
    }

    public List<Granjero> getGranjeros(Granjero g) {
        try (Session session = HibernateUtil.getCurrentSession()) {
            session.beginTransaction();
            String queryString = getQueryString(g);
            Query<Granjero> query = session.createQuery(queryString, Granjero.class);
            setQueryParameters(query, g);
            List<Granjero> granjeros = query.list();
            session.getTransaction().commit();
            return granjeros;
        } catch (Exception e) {
            System.out.println("Error en List<Granjero> getGranjeros(Granjero g) | " + e.getMessage());
            return null;
        }
    }

    private static String getQueryString(Granjero g) {
        StringBuilder queryString = new StringBuilder("FROM Granjero WHERE 1=1");

        if (g.getId() != null)          queryString.append(" AND id = :id");
        if (g.getNombre() != null)      queryString.append(" AND nombre = :nombre");
        if (g.getDescripcion() != null) queryString.append(" AND descripcion = :descripcion");
        if (g.getDinero() != null)      queryString.append(" AND dinero = :dinero");
        if (g.getPuntos() != null)      queryString.append(" AND puntos = :puntos");
        if (g.getNivel() != null)       queryString.append(" AND nivel = :nivel");

        return queryString.toString();
    }

    private void setQueryParameters(Query<Granjero> query, Granjero g) {
        if (g.getId() != null)          query.setParameter("id", g.getId());
        if (g.getNombre() != null)      query.setParameter("nombre", g.getNombre());
        if (g.getDescripcion() != null) query.setParameter("descripcion", g.getDescripcion());
        if (g.getDinero() != null)      query.setParameter("dinero", g.getDinero());
        if (g.getPuntos() != null)      query.setParameter("puntos", g.getPuntos());
        if (g.getNivel() != null)       query.setParameter("nivel", g.getNivel());
    }

    public boolean setGranjero(Granjero g) {
        try (Session session = HibernateUtil.getCurrentSession()) {
            session.beginTransaction();
            session.update(g);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println("Error en boolean setGranjero(Granjero g) | " + e.getMessage());
            return false;
        }
    }

    public boolean removeGranjero(int id) {
        try (Session session = HibernateUtil.getCurrentSession()) {
            session.beginTransaction();
            Query<Granjero> query = session.createQuery("FROM Granjero WHERE id = :id", Granjero.class);
            query.setParameter("id", id);
            Granjero granjero = query.uniqueResult();
            if (granjero != null) {
                session.delete(granjero);
                session.getTransaction().commit();
                return true;
            } else {
                System.out.println("No se encontró un granjero con el ID proporcionado.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error en boolean removeGranjero(int id) | " + e.getMessage());
            return false;
        }
    }

    public boolean addGranjero(Granjero g) {
        try (Session session = HibernateUtil.getCurrentSession()) {
            session.beginTransaction();
            session.save(g);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println("Error en boolean addGranjero(Granjero g) | " + e.getMessage());
            return false;
        }
    }
}
