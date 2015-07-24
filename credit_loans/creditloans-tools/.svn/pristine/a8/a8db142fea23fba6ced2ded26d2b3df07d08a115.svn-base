package com.intellicredit.platform.component.situation2;

import com.intellicredit.platform.util.ComparableCalendar;
import java.io.Serializable;
import java.util.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//利用thread-safe的HashMap来存放key:value
public class Situation implements Serializable, Cloneable {

	private static final long serialVersionUID = -6451941683863847104L;
	private Map<String,Object> m_attributeMap;

	public Situation() {
		m_attributeMap = Collections.synchronizedMap(new HashMap<String,Object>());// thread-safe
	}

	public Object clone() throws CloneNotSupportedException {
		Situation situation = new Situation();
		synchronized (m_attributeMap) {
			Set<String> set = m_attributeMap.keySet();
			for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {
				String s = (String) iterator.next();
				Object obj1 = m_attributeMap.get(s);
				if (obj1 != null) {
					if (obj1 instanceof ComparableCalendar)
						obj1 = new Date(((ComparableCalendar) obj1).getTime()
								.getTime());
					situation.setAttribute(s, obj1);
				}
			}

		}
		return situation;
	}

	public String toString() {
		String s1 = null;
		StringBuffer stringbuffer = new StringBuffer("Situation:\n");
		synchronized (m_attributeMap) {
			if (m_attributeMap.size() == 0) {
				stringbuffer.append("Empty\n");
				String s2 = stringbuffer.toString();
				return s2;
			}
			Set<String> set = m_attributeMap.keySet();
			String s;
			for (Iterator<String> iterator = set.iterator(); iterator.hasNext(); stringbuffer
					.append("-").append(s).append(" = ").append(s1)
					.append("\n")) {
				s = (String) iterator.next();
				Object obj1 = m_attributeMap.get(s);
				if (obj1 instanceof String)
					s1 = "(text) \"" + obj1 + "\"";
				if (obj1 instanceof ComparableCalendar)
					s1 = "(date) "
							+ ((ComparableCalendar) obj1).getTime().toString();
				if (obj1 instanceof Number)
					s1 = "(number) " + ((Number) obj1).toString();
			}

		}
		return stringbuffer.toString();
	}

	public Object getAttribute(String s) {
		Object obj = m_attributeMap.get(s);
		if (obj instanceof ComparableCalendar)
			return ((ComparableCalendar) obj).getTime();
		else
			return obj;
	}

	public Date getDateAttribute(String s) throws ClassCastException {
		Object obj = m_attributeMap.get(s);
		if (obj == null) {
			return null;
		} else {
			ComparableCalendar comparablecalendar = (ComparableCalendar) obj;
			return comparablecalendar.getTime();
		}
	}

	public Number getNumberAttribute(String s) throws ClassCastException {
		Object obj = m_attributeMap.get(s);
		if (obj == null)
			return null;
		else
			return (Number) obj;
	}

	public String getTextAttribute(String s) throws ClassCastException {
		Object obj = m_attributeMap.get(s);
		if (obj == null)
			return null;
		else
			return (String) obj;
	}

	public void setTextAttribute(String s, String s1) {
		m_attributeMap.put(s, s1);
	}

	public void setNumberAttribute(String s, Number number) {
		m_attributeMap.put(s, number);
	}

	public void setDateAttribute(String s, Date date) {
		m_attributeMap.put(s, new ComparableCalendar(date.getTime()));
	}

	public void setAttribute(String s, Object obj) {
		m_attributeMap.put(s, obj);
	}

	public int size() {
		return m_attributeMap.size();
	}

	public Set<String> getAttributeNames() {
		return m_attributeMap.keySet();
	}

	public void removeAttribute(String s) {
		m_attributeMap.remove(s);
	}

	public void clear() {
		m_attributeMap.clear();
	}

	/*
	 * Alias' definition indicates a class's method. It is independent of
	 * object.Alias's value is independent of an object and value of the
	 * parameters passed to the method. So to get the value of the alias. we
	 * must specify the names of the object and parameter; or names of the
	 * object and values of the parameter; or the reference of the object and
	 * value of the parameters.
	 */
	/**
	 * @param aliasName
	 *            used as a key for Method methodName in Situation
	 * @param className
	 *            used to get the Method object with name equals to methodName
	 * @param methodName
	 *            the name of a method in class className
	 * 
	 * @param args
	 *            arguments passed to method called methodName
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public void setAliasAttribute(String aliasName, String className,
			String methodName, Class<?>[] args) throws ClassNotFoundException,
			InstantiationException, NoSuchMethodException,
			InvocationTargetException, IllegalAccessException {
		Class<?> cls = (Class<?>) m_attributeMap.get(className);
		if (cls == null) {
			cls = Class.forName(className);
			m_attributeMap.put(className, cls);
		}

		Method method = (Method) m_attributeMap.get(aliasName);
		if (method == null) {
			m_attributeMap.put(aliasName, cls.getMethod(methodName, args));
		}
	}

	public Object getAliasAttribute(String aliasName, Object obj,
			ArgumentHolder args) throws IllegalAccessException,
			InvocationTargetException {
		Method method = (Method) m_attributeMap.get(aliasName);
		return (method.invoke(obj, args.getArguments()));
	}

	public Object getAliasAttribute(String aliasName, String objectName,
			ArgumentHolder args) throws IllegalAccessException,
			InvocationTargetException {
		Method method = (Method) m_attributeMap.get(aliasName);
		return (method.invoke(m_attributeMap.get(objectName),
				args.getArguments()));
	}

	public Object getAliasAttribute(String aliasName, String objectName,
			String[] argNames) throws IllegalAccessException,
			InvocationTargetException {
		ArgumentHolder holder = new ArgumentHolder();
		for (int i = 0; i < argNames.length; i++) {
			holder.setArgument(m_attributeMap.get(argNames[i]));
		}
		Method method = (Method) m_attributeMap.get(aliasName);
		return (method.invoke(m_attributeMap.get(objectName),
				holder.getArguments()));
	}

	public void setScriptAttribute(String name, String script) {
		m_attributeMap.put(name, script);
	}

	public Object getScriptAttribute(String name) {
		String script = (String) m_attributeMap.get(name);
		return (Object) script;
	}

	Map<String,Variable> m_variables = new LinkedHashMap<String,Variable>();

	public Map<String,Variable> getVariables() {
		return m_variables;
	}

	public void setVariable(String name, Variable var) {
		m_variables.put(name, var);
	}

	public Variable getVariable(String name) throws ClassCastException {
		return (Variable) m_variables.get(name);
	}

	public void reset(Object obj) {

	}
}