package com.backend.tcc.perfil.enums;

public enum PerfilEnum {
	ADMIN(1), 
	SUPERVISOR(2), 
	TRABALHADOR(3),
	CLIENTE(4);

	PerfilEnum(Integer id)
	{
		this.ID = id;
	}

	public final Integer ID;

	public static PerfilEnum get(Integer id)
	{
		if (id == null)
		{
			return null;
		}

		for (PerfilEnum e : PerfilEnum.values())
		{
			if (e.ID.equals(id))
			{
				return e;
			}
		}

		return null;
	}
}
