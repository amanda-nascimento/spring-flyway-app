package org.meli.apptestemigration.services;

import org.meli.apptestemigration.DTO.PessoaDTO;
import org.meli.apptestemigration.model.Pessoa;
import org.meli.apptestemigration.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;


    public void criar(PessoaDTO dto) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(dto.getNome());
        pessoa.setIdade(dto.getIdade());
        repository.save(pessoa);
    }

    public void atualizar(Long id, PessoaDTO dto) {
        Pessoa pessoa = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
        pessoa.setNome(dto.getNome());
        repository.save(pessoa);
    }

    public void deletar(Long id) {
        Pessoa pessoa = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
        repository.delete(pessoa);
    }

    public List<PessoaDTO> listarTodos() {
        List<Pessoa> pessoas = repository.findAll();
        List<PessoaDTO> pessoasDTOs = new ArrayList<>();

        for (Pessoa pessoa : pessoas) {
            PessoaDTO pessoaDTO = new PessoaDTO();
            pessoaDTO.setNome(pessoa.getNome());
            pessoaDTO.setIdade(pessoa.getIdade());
            pessoasDTOs.add(pessoaDTO);
        }

        return pessoasDTOs;
    }

    public PessoaDTO buscarPorId(Long id) {
        Pessoa pessoa = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setNome(pessoa.getNome());
        pessoaDTO.setIdade(pessoa.getIdade());
        return pessoaDTO;
    }

    @Scheduled(fixedRate = 30000)
    public int corrigirNomesDasPessoas() {
        System.out.println("Executando corrigirNomesDasPessoas");
        List<Pessoa> pessoas = repository.findAll();
        int alterados = 0;

        if (pessoas.isEmpty()) {
            return 0;
        }

        for (Pessoa p : pessoas) {
            String nomeOriginal = p.getNome();
            String nomeCorrigido = formatarNome(nomeOriginal);

            if (!nomeOriginal.equals(nomeCorrigido)) {
                p.setNome(nomeCorrigido);
                alterados++;
            }
        }

        repository.saveAll(pessoas);
        return alterados;
    }

    private String formatarNome(String nome) {
        if (nome == null || nome.isBlank()) return nome;
        return nome.substring(0, 1).toUpperCase() + nome.substring(1).toLowerCase();
    }


}
