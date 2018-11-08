package net.azurewebsites.fatecsjc.resources.test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import net.azurewebsites.fatecsjc.entity.Cep;
import net.azurewebsites.fatecsjc.entity.Cidade;
import net.azurewebsites.fatecsjc.entity.Estado;
import net.azurewebsites.fatecsjc.entity.TipoLocalidade;
import net.azurewebsites.fatecsjc.entity.Localidade;
import net.azurewebsites.fatecsjc.entity.TipoCampanha;
import net.azurewebsites.fatecsjc.entity.TipoContato;
import net.azurewebsites.fatecsjc.entity.TipoResponsavel;
import net.azurewebsites.fatecsjc.entity.Responsavel;
import net.azurewebsites.fatecsjc.entity.Contato;
import net.azurewebsites.fatecsjc.repository.CepRepository;
import net.azurewebsites.fatecsjc.repository.CidadeRepository;
import net.azurewebsites.fatecsjc.repository.EstadoRepository;
import net.azurewebsites.fatecsjc.repository.TipoLocalidadeRepository;
import net.azurewebsites.fatecsjc.repository.LocalidadeRepository;
import net.azurewebsites.fatecsjc.repository.TipoCampanhaRepository;
import net.azurewebsites.fatecsjc.repository.TipoContatoRepository;
import net.azurewebsites.fatecsjc.repository.TipoResponsavelRepository;
import net.azurewebsites.fatecsjc.repository.ResponsavelRepository;
import net.azurewebsites.fatecsjc.repository.CampanhaRepository;
import net.azurewebsites.fatecsjc.repository.ContatoRepository;

import net.azurewebsites.fatecsjc.entity.Usuario;
import net.azurewebsites.fatecsjc.entity.dto.Campanha;
import net.azurewebsites.fatecsjc.enums.Perfil;
import net.azurewebsites.fatecsjc.entity.Preferencia;
import net.azurewebsites.fatecsjc.repository.UsuarioRepository;
import net.azurewebsites.fatecsjc.service.PreferenciaService;
import net.azurewebsites.fatecsjc.service.TipoCampanhaService;
import net.azurewebsites.fatecsjc.repository.PreferenciaRepository;

@Service
public class DBTest {
	
	@Autowired
	private BCryptPasswordEncoder pwd;
	
	@Autowired
	EstadoRepository estadoReposotory;
	
	@Autowired
	CidadeRepository cidadeRepository;
	
	@Autowired
	CepRepository cepRepository;
	
	@Autowired
	TipoLocalidadeRepository tipoLocalidadeRepository;
	
	@Autowired
	LocalidadeRepository localidadeRepository;
	
	@Autowired
	TipoCampanhaRepository tipoCampanhaRepository;
	
	@Autowired
	TipoContatoRepository tipoContatoRepository;
	
	@Autowired
	TipoResponsavelRepository tipoResponsavelRepository;
	
	@Autowired
	ResponsavelRepository responsavelRepository;
	
	@Autowired
	CampanhaRepository campanhaRepository;
	
	@Autowired
	ContatoRepository contatoRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	PreferenciaRepository preferenciaRepository;
	
	@Autowired
	PreferenciaService preferenciaService;
	
	@Autowired
	TipoCampanhaService tipoCampanhaService;
		
	
	public void initialize() {
		Estado e1 = new Estado(null, "São Paulo", "SP");
		Estado e2 = new Estado(null, "Rio de Janeiro", "RJ");
		estadoReposotory.saveAll(Arrays.asList(e1, e2));
		
		Cidade c1 = new Cidade(null, "São José dos Campos", e1);
		Cidade c2 = new Cidade(null, "Barra Mansa", e2);
		Cidade c3 = new Cidade(null, "Bovinos", e2);
		Cidade c4 = new Cidade(null, "Barra Bonita", e2);
		Cidade c5 = new Cidade(null, "Barracuda", e1);
		cidadeRepository.saveAll(Arrays.asList(c1,c2, c3, c4, c5));
		
		Cep cp1 = new Cep(null, "12244650", "Rua Armando Lourenço", "Esplanada do Sol", c1);
		Cep cp2 = new Cep(null, "13315225", "Rua Barra Mansa", "Mansa", c2);
		Cep cp3 = new Cep(null, "12244021", "Rua Jose Maria Monteiro", "Vila Zizinha", c1);
		cepRepository.saveAll(Arrays.asList(cp1,cp2, cp3));
		
		TipoLocalidade tploc1 = new TipoLocalidade(null, "Hospital");
		TipoLocalidade tploc2 = new TipoLocalidade(null, "Prefeitura");
		tipoLocalidadeRepository.saveAll(Arrays.asList(tploc1, tploc2));
		
		Localidade loc1 = new Localidade(null, "Localidade SJC", "Referencia", 300, "APTO 12", tploc1, cp1);
		Localidade loc2 = new Localidade(null, "Localidade Barra Mansa", "Referencia Hospital", 254, "Fundos", tploc2, cp2);
		Localidade loc3 = new Localidade(null, "Localidade SJC2", "Referencia", 200, "APT 34 A", tploc1, cp3);
		localidadeRepository.saveAll(Arrays.asList(loc1, loc2, loc3));
		
		TipoCampanha tpc1 = new TipoCampanha(null, "Tipo Campanha 1");
		TipoCampanha tpc2 = new TipoCampanha(null, "Tipo Campanha 2");		
		tipoCampanhaRepository.saveAll(Arrays.asList(tpc1, tpc2));
		
		TipoContato tpcont1 = new TipoContato(null, "Telefone");
		TipoContato tpcont2 = new TipoContato(null, "Email");
		tipoContatoRepository.saveAll(Arrays.asList(tpcont1, tpcont2));
		
		TipoResponsavel tpresp1 = new TipoResponsavel(null, "Pessoa Fisica");
		TipoResponsavel tpresp2 = new TipoResponsavel(null, "Pessoa Juridica");
		tipoResponsavelRepository.saveAll(Arrays.asList(tpresp1, tpresp2));
		
		Responsavel resp1 = new Responsavel(null, "Rafael", "41847593860", "1992-05-29", "M", 285, "", tpresp1, cp1);
		Responsavel resp2 = new Responsavel(null, "Empresa", "6471292800011", "Razao Social", 33, "Fundos", tpresp2, cp2);
		responsavelRepository.saveAll(Arrays.asList(resp1, resp2));
		
		Campanha camp1 = new Campanha(null, "CampanhaA", "2018-07-01", "2018-09-01","2018-10-29", "Descricao da campanha", resp1, tpc1);
		Campanha camp2 = new Campanha(null, "CampanhaB", "2017-01-01", "2017-06-30","2018-10-29", "Descricao de outra campanha", resp1, tpc2);
		Campanha camp3 = new Campanha(null, "CampanhaC", "2018-09-01", "2018-10-01","2018-10-29", "Descricao", resp2, tpc2);
		Campanha camp4 = new Campanha(null, "CampanhaD", "2018-08-20", "2018-08-25","2018-10-29", "Descricao", resp1, tpc1);
		campanhaRepository.saveAll(Arrays.asList(camp1, camp2, camp3, camp4));
		
		
		Contato cont1 = new Contato(null, "Nelson", "bobby@pai.com", tpcont2, resp1);
		Contato cont2 = new Contato(null, "Contatinho", "98829585", tpcont1, resp2);
		Contato cont3 = new Contato(null, "ContLocalidade", "9895659898", tpcont1, loc1);
		Contato cont4 = new Contato(null, "ContLocalidade2", "222222222", tpcont1, loc1);
		Contato cont5 = new Contato(null, "ContLocalidade3", "email@email.com", tpcont2, loc1);
		contatoRepository.saveAll(Arrays.asList(cont1, cont2, cont3, cont4, cont5));
		
		Preferencia pref1 = new Preferencia(null, "preferencia1", "pref1");
		Preferencia pref2 = new Preferencia(null, "preferencia2", "pref2");
		preferenciaRepository.saveAll(Arrays.asList(pref1, pref2));

		Set<TipoCampanha> tipoCampanha = new HashSet<>();
		tipoCampanha.add(tipoCampanhaService.findById(tpc1.getId()));
		tipoCampanha.add(tipoCampanhaService.findById(tpc2.getId()));
		
		
		Usuario user1 = new Usuario(null, "Usuario1", "29/05/1992", "00000000011", "M", "email", pwd.encode("123"), "2018-10-15");
		user1.setTipoCampanha(tipoCampanha);

		Usuario user2 = new Usuario(null, "Usuario2", "14/12/1992", "00000000012", "F", "email2", pwd.encode("456"), "2018-01-01");

		Usuario user3 = new Usuario(null, "ADM", null, null, null, "admin", pwd.encode("123"), null);
		user3.addPerfil(Perfil.ADMIN);
		
		usuarioRepository.saveAll(Arrays.asList(user1, user2, user3));
		
	}
}
