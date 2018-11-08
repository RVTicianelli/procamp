package net.azurewebsites.fatecsjc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.azurewebsites.fatecsjc.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	@Transactional(readOnly = true)
	@Query("SELECT usuario FROM Usuario usuario WHERE usuario.id = :id")
	public Usuario buscaPorId(@Param("id") Integer id);

	@Transactional(readOnly = true)
	public List<Usuario> findByNome(String nome);
	
	@Transactional(readOnly = true)
	public List<Usuario> findBySexo(String sexo);
	
	@Transactional(readOnly = true)
	public Usuario findByEmail(String email);
	
	@Transactional(readOnly = true)
	public Usuario findByCpf(String cpf);
	
	@Transactional(readOnly = true)
	@Query("SELECT usuario FROM Usuario usuario WHERE usuario.cpf = :cpf or usuario.email = :email")
	public Usuario findByCpfAndEmail(@Param("cpf") String cpf, @Param("email") String email);
	
	@Transactional(readOnly = true)
	@Query("SELECT usuario FROM Usuario usuario WHERE usuario.email = :email or usuario.senha = :senha")
	public Usuario findByEmailAndPassword(@Param("email") String email, @Param("senha") String senha);		
}
