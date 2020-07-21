package com.group.cast.diffator.service;

import com.group.cast.diffator.Utils;
import com.group.cast.diffator.domain.Diff;
import com.group.cast.diffator.dto.DiffResponseDTO;
import com.group.cast.diffator.repository.DiffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class DiffService {

    @Autowired
    public DiffRepository diffRepository;

    public DiffResponseDTO checkDiff(Long id) {

        String message = "";
        Optional<Diff> byId = diffRepository.findById(id);
        if (byId.isPresent()) {

            Diff diff = byId.get();

            String left = diff.getLeft();

            String right = diff.getRight();

            if (left == null || right == null) {
                message = "Documentos sem diff completamente configurado.";
            } else {
                if (left.length() != right.length()) {
                    message = "Documentos " + id + " com tamanhos diferentes.";
                } else {
                    int i = Utils.indexOfDifference(left, right);
                    if (i < 0) {
                        message = "Documentos " + id + " idênticos.";
                    } else {
                        message = "Offset: " + i;
                    }
                }
            }
        } else {
            message = "Documento inexistente!";
        }

        return new DiffResponseDTO(message);
    }

    public void createRightDoc(Long id, String json) {
        Optional<Diff> byId = diffRepository.findById(id);
        if (byId.isPresent()) {
            diffRepository.save(byId.get().setRight(json));
        } else {
            Diff diff = new Diff().setId(id).setRight(json);
            diffRepository.save(diff);
        }
    }

    public void createLeftDoc(Long id, String json) {
        Optional<Diff> byId = diffRepository.findById(id);
        if (byId.isPresent()) {
            diffRepository.save(byId.get().setLeft(json));
        } else {
            Diff diff = new Diff().setId(id).setLeft(json);
            diffRepository.save(diff);
        }
    }
}
