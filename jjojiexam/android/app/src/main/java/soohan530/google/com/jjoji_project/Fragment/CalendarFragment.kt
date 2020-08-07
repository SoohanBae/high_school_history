package soohan530.google.com.jjoji_project.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.CalendarMode
import com.prolificinteractive.materialcalendarview.MaterialCalendarView


import soohan530.google.com.jjoji_project.R
import java.util.*
import android.widget.Toast
import android.support.annotation.NonNull
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import android.util.Log
import java.util.concurrent.Executors
import android.os.AsyncTask
import kotlinx.android.synthetic.main.fragment_calendar.*
import soohan530.google.com.jjoji_project.Activity.MainActivity

import android.graphics.Color
import com.prolificinteractive.materialcalendarview.DayViewFacade
import android.app.Activity
import android.graphics.drawable.Drawable
import com.prolificinteractive.materialcalendarview.DayViewDecorator

import com.prolificinteractive.materialcalendarview.spans.DotSpan
import kotlinx.android.synthetic.main.fragment_calendar.view.*


class CalendarFragment : Fragment() {


    lateinit var calendarView: MaterialCalendarView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(soohan530.google.com.jjoji_project.R.layout.fragment_calendar, container, false)

        calendarView = view.findViewById<MaterialCalendarView>(soohan530.google.com.jjoji_project.R.id.calendarView)
        calendarView.state().edit()
            .setFirstDayOfWeek(Calendar.SUNDAY)
            .setMinimumDate(CalendarDay.from(2017, 0, 1))
            .setMaximumDate(CalendarDay.from(2030, 11, 31))
            .setCalendarDisplayMode(CalendarMode.MONTHS)
            .commit()
        calendarView.setTileHeightDp(40)


        calendarView.selectionMode = 1
        calendarView.addDecorators(
                SundayDecorator(),
                SaturdayDecorator())

//                OneDayDecorator())
//
        val result = arrayOf("2019,03,18", "2019,04,18", "2019,05,18", "2019,06,18")

        ApiSimulator(result).executeOnExecutor(Executors.newSingleThreadExecutor())

        calendarView.setOnDateChangedListener(OnDateSelectedListener { widget, date, selected ->
            val Year = date.year
            val Month = date.month + 1
            val Day = date.day

            Log.i("Year test", Year.toString() + "")
            Log.i("Month test", Month.toString() + "")
            Log.i("Day test", Day.toString() + "")

            val shot_Day = "$Year,$Month,$Day"

            Log.i("shot_Day test", shot_Day + "")
            calendarView.clearSelection()

            Toast.makeText(MainActivity.mContext, shot_Day, Toast.LENGTH_SHORT).show()
        })

        return view
    }

    inner class ApiSimulator internal constructor(internal var Time_Result: Array<String>) :
        AsyncTask<Void, Void, List<CalendarDay>>() {

        override fun doInBackground(vararg voids: Void): List<CalendarDay> {
            try {
                Thread.sleep(500)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            val calendar = Calendar.getInstance()
            val dates = ArrayList<CalendarDay>()

            /*특정날짜 달력에 점표시해주는곳*/
            /*월은 0이 1월 년,일은 그대로*/
            //string 문자열인 Time_Result 을 받아와서 ,를 기준으로짜르고 string을 int 로 변환
            for (i in Time_Result.indices) {
                val day = CalendarDay.from(calendar)
                val time = Time_Result[i].split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                val year = Integer.parseInt(time[0])
                val month = Integer.parseInt(time[1])
                val dayy = Integer.parseInt(time[2])

                dates.add(day)
                calendar.set(year, month - 1, dayy)
            }



            return dates
        }

        override fun onPostExecute(calendarDays: List<CalendarDay>) {
            super.onPostExecute(calendarDays)
//
//            if (isFinishing()) {
//                return
//            }

            calendarView.addDecorator(EventDecorator(Color.DKGRAY, calendarDays))
        }
    }

    inner class EventDecorator(private val color: Int, dates: Collection<CalendarDay>) :
        DayViewDecorator {

        private val dates: HashSet<CalendarDay>

        init {

            this.dates = HashSet(dates)
        }

        override fun shouldDecorate(day: CalendarDay): Boolean {
            return dates.contains(day)
        }

        override fun decorate(view: DayViewFacade) {
            //view.setSelectionDrawable(drawable)
            view.addSpan(DotSpan("5.0".toFloat(), color)) // 날자밑에 점
        }
    }


}
