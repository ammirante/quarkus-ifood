package com.github.ammirante.ifood.cadastro.util;

import java.util.Arrays;

/**
 * ToString
 *
 */
public final class ToString {
	
	/**
	 * 
	 */
	private ToString() {
	}

	/**
	 * Retorna o String da classe, sem considerar os atributos do pacote com.github.ammirante.ifood
	 * @param <T>
	 *
	 * @return
	 */
	public static <T> String build(T objeto) {
		final StringBuilder toString = new StringBuilder();
		toString.append("\n===>");
		toString.append(objeto.getClass().getSimpleName());
		toString.append("[");

		Arrays.asList(objeto.getClass().getDeclaredFields()).stream()
				.filter(campo -> !campo.getName().equals("serialVersionUID") && (campo.getType().getPackage() != null
						&& !campo.getType().getPackage().getName().contains("com.github.ammirante.ifood")))
				.forEach(campo -> {
					toString.append(campo.getName());
					campo.setAccessible(true);
					toString.append("=");
					try {
						toString.append(campo.get(objeto));
					} catch (IllegalArgumentException | IllegalAccessException e) {
						toString.append("<Erro: ").append(e.getMessage()).append(">");
					} finally {
						campo.setAccessible(false);
					}

					toString.append(",");
				});

		toString.delete(toString.length() - 1, toString.length());
		toString.append("]");

		return toString.toString();
	}
}