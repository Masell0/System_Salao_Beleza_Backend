package com.backend.tcc.agendamentos.enums;

public enum AgendamentosStatusEnum {
	AGUARDANDO_CONFIRMACAO(0), 
	CONFIRMADO(1), 
	CANCELADO(2);

	AgendamentosStatusEnum(Integer id)
	{
		this.ID = id;
	}

	public final Integer ID;

	public static AgendamentosStatusEnum get(Integer id)
	{
		if (id == null)
		{
			return null;
		}

		for (AgendamentosStatusEnum e : AgendamentosStatusEnum.values())
		{
			if (e.ID.equals(id))
			{
				return e;
			}
		}

		return null;
	}
}
