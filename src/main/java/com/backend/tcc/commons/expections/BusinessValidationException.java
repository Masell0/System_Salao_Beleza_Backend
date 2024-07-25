package com.backend.tcc.commons.expections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class BusinessValidationException extends RuntimeException
{
	private List<Object> errors;
	private Map<String, List<Object>> fieldErrors;
	private List<BusinessValidationException> list;
	private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
	private Long index;
	
	public BusinessValidationException()
	{
		
	}
	
	public BusinessValidationException(String stringCode)
	{
		addError(stringCode);
	}
	
	public BusinessValidationException(String stringCode, Throwable cause)
	{
        super(cause);
		addError(stringCode);
	}
	
	public BusinessValidationException(MessageVariables messageVariables)
	{
		addError(messageVariables);
	}

	public String getMessage()
    {
        return toJsonString();
    }
	
	public BusinessValidationException(String stringCode, HttpStatus httpStatus)
	{
		addError(stringCode);
		this.httpStatus = httpStatus;
	}
	
	public BusinessValidationException(HttpStatus httpStatus)
	{
		this.httpStatus = httpStatus;
	}
	
	public BusinessValidationException(String fieldName, String fieldError)
	{
		addFieldErrorsFinal(fieldName, fieldError);
	}

	public void addErrorList(long index, BusinessValidationException item)
	{
    	if (!item.hasErrors())
    	{
    		return;
    	}
    	
    	if (list == null)
    	{
    		list = new ArrayList<>();
    	}
    	
    	item.setIndex(index);
    	
    	list.add(item);
	}

	
    public void addError(Object stringCode)
	{
    	if (errors == null)
		{
    		errors = new ArrayList<>();
		}
    	
    	errors.add(stringCode);
	}
	
	public void addFieldErrors(String field, String error)
	{
		addFieldErrorsFinal(field, error);
	}
	
	private void addFieldErrorsFinal(String field, Object error)
	{
		if (fieldErrors == null)
		{
			fieldErrors = new HashMap<>();
		}
		
		List<Object> errors = fieldErrors.get(field);
		
		if (errors == null)
		{
			errors = new ArrayList<>();
			fieldErrors.put(field, errors);
		}
		
		errors.add(error);
	}
	
	public void throwIfExistsErrors()
	{
		if (hasErrors())
		{
			throw this;
		}
	}

	public boolean hasErrors()
	{
		return (errors != null && !errors.isEmpty()) || (fieldErrors != null && !fieldErrors.isEmpty()) || (list != null && !list.isEmpty());
	}

	public void fieldNotNull(String fieldName, String value)
	{
		fieldValidate(fieldName, !StringUtils.isAllBlank(value), "required");
	}	

	public void fieldNotNull(String fieldName, Object value)
	{
		fieldValidate(fieldName, value != null, "required");
	}	

	public void fieldNotEmpty(String fieldName, Object value)
	{
		fieldValidate(fieldName, value != null, "required");
	}
	
	public void fieldNotEmpty(String fieldName, String value)
	{
		fieldValidate(fieldName, !StringUtils.isAllBlank(value), "required");
	}

	public void fieldValidate(String fieldName, boolean isValid, String message)
	{
		if (!isValid)
		{
			addFieldErrorsFinal(fieldName, message);
		}
	}
	
	public void fieldValidate(String fieldName, boolean isValid, Supplier<MessageVariables> supplier)
	{
		if (!isValid)
		{
			addFieldErrorsFinal(fieldName, supplier.get());
		}
	}
	
	private JSONObject toJson()
	{
		JSONObject errorResponse = new JSONObject();
		
		if (errors != null)
		{
			errorResponse.put("errors", errors);
		}
		
		if (fieldErrors != null)
		{
			errorResponse.put("fieldErrors", new JSONObject(fieldErrors));
		}
		
		if (index != null)
		{
			errorResponse.put("index", index);
		}
		
		if (list != null)
		{
			JSONArray array = new JSONArray();
			
			for (BusinessValidationException item : list)
			{
				array.put(item.toJson());
			}
			
			errorResponse.put("list", array);
		}
		
		return errorResponse;
	}
	
	public String toJsonString()
	{
		
		return toJson().toString();
	}
	
	@Override
	public String toString()
	{
		return toJsonString();
	}

	public HttpStatus getHttpStatus()
	{
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus)
	{
		this.httpStatus = httpStatus;
	}

	public List<Object> getErrors()
	{
		return errors;
	}

	public Map<String, List<Object>> getFieldErrors()
	{
		return fieldErrors;
	}
	
	public static class MessageVariables
	{
		private String key;
		private Map<String, Object> variables = new HashMap<>();
		
		public MessageVariables()
		{
			super();
		}
		
		public MessageVariables(String key)
		{
			this.key = key;
		}
		
		public String getKey()
		{
			return key;
		}
		public void setKey(String key)
		{
			this.key = key;
		}
		public Map<String, Object> getVariables()
		{
			return variables;
		}
		public void setVariables(Map<String, Object> variables)
		{
			this.variables = variables;
		}
		
		public MessageVariables add(String key, Object value)
		{
			variables.put(key, value);
			return this;
		}
	}

	public Long getIndex()
	{
		return index;
	}

	public void setIndex(Long index)
	{
		this.index = index;
	}
}
