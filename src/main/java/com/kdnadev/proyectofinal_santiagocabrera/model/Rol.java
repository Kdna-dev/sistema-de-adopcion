package com.kdnadev.proyectofinal_santiagocabrera.model;

public enum Rol {
    ADMIN(1),
    DOCTOR(2),
    CLIENTE(3);

    private final int codigo;

    Rol(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public static Rol fromCodigo(int codigo) {
        for (Rol rol : Rol.values()) {
            if (rol.getCodigo() == codigo) {
                return rol;
            }
        }
        throw new IllegalArgumentException("Código de rol inválido: " + codigo);
    }
}