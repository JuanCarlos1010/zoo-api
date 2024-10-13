package com.openx.zoo.api.repositories;

import com.openx.zoo.api.models.Permission;
import com.openx.zoo.api.models.Role;
import com.openx.zoo.api.models.RolePermission;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class RolePermissionRepository  {
    @PersistenceContext
    private EntityManager entityManager;

    public Iterable<Permission> findPermissions() {
        try (Session session = entityManager.unwrap(Session.class)) {
            return session.createQuery("FROM Permission", Permission.class)
                    .getResultList();
        }
    }

    public Iterable<Permission> findPermissionsByRole(long role) {
        try (Session session = entityManager.unwrap(Session.class)) {
            String strSQL = "SELECT rp.permission FROM RolePermission rp WHERE rp.role.id = :role";

            return session.createQuery(strSQL, Permission.class)
                    .setParameter("role", role)
                    .getResultList();
        }
    }

    public Optional<Permission> findPermissionById(long permissionId) {
        try (Session session = entityManager.unwrap(Session.class)) {
            Permission result = session.createQuery("FROM Permission WHERE id = :permissionId", Permission.class)
                    .setParameter("permissionId", permissionId)
                    .getSingleResult();
            return Optional.ofNullable(result);
        } catch (NonUniqueResultException e) {
            return Optional.empty();
        }
    }

    public Optional<Role> findRoleById(long roleId) {
        try (Session session = entityManager.unwrap(Session.class)) {
            Role result = session.createQuery("FROM Role WHERE id = :roleId", Role.class)
                    .setParameter("roleId", roleId)
                    .getSingleResult();
            return Optional.ofNullable(result);
        } catch (NoResultException e) {
            return Optional.empty();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Role> findRoleByName(String roleName) {
        try (Session session = entityManager.unwrap(Session.class)) {
            Role result = session.createQuery("FROM Role WHERE name = :roleName", Role.class)
                    .setParameter("roleName", roleName)
                    .getSingleResult();
            return Optional.ofNullable(result);
        } catch (NoResultException e) {
            return Optional.empty();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Role createRole(Role role) {
        Session session = entityManager.unwrap(Session.class);
        Transaction transaction = session.beginTransaction();
        try {
            Role response = session.merge(role);
            transaction.commit();
            session.close();
            return response;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            throw new RuntimeException(e);
        }
    }

    public RolePermission createRolePermission(RolePermission permission) {
        Session session = entityManager.unwrap(Session.class);
        Transaction transaction = session.beginTransaction();
        try {
            RolePermission response = session.merge(permission);
            transaction.commit();
            session.close();
            return response;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            throw new RuntimeException(e);
        }
    }

    public boolean deleteRolePermission(long id) {
        Session session = entityManager.unwrap(Session.class);
        Transaction transaction = session.beginTransaction();
        try {
            String stringSQL = "DELETE FROM role_permissions WHERE id = :id";
            int affected = session.createNativeQuery(stringSQL, Void.class)
                    .setParameter("id", id)
                    .executeUpdate();
            transaction.commit();
            return affected > 0;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            throw new RuntimeException(e);
        }
    }
}
