package cn.creditloans.tools.validator.util;

import java.util.Date;
import java.util.GregorianCalendar;

public class ComparableCalendar extends GregorianCalendar implements Cloneable {

	private static final long serialVersionUID = 4667832440327756522L;

	public ComparableCalendar() {
	}

	public ComparableCalendar(Date date) {
		super.setTime(date);
	}

	public ComparableCalendar(long l) {
		super.setTime(new Date(l));
	}

	public ComparableCalendar clearTimeFields() {
		super.set(14, 0);
		super.set(10, 0);
		super.set(11, 0);
		super.set(12, 0);
		super.set(13, 0);
		return this;
	}

	public int compareTo(ComparableCalendar obj) {
		ComparableCalendar comparablecalendar = null;
		if (!(obj instanceof ComparableCalendar))
			throw new ClassCastException(
					"Can only compare ComparableCalendar with a ComparableCalendar");
		comparablecalendar = (ComparableCalendar) obj;
		long l = super.getTime().getTime();
		long l1 = comparablecalendar.getTime().getTime();
		if (l > l1)
			return 1;
		return l >= l1 ? 0 : -1;
	}

	public Object clone() {
		ComparableCalendar comparablecalendar = null;
		comparablecalendar = new ComparableCalendar(getTime().getTime());
		return comparablecalendar;
	}

	public long getTimeAsLong() {
		int i = super.get(11);
		int j = super.get(12);
		int k = super.get(13);
		int l = super.get(15);
		long l1 = 1000 * k + 60000 * j + 0x36ee80 * i + l;
		return l1;
	}
}
