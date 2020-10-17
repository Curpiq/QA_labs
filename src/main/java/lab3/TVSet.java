package lab3;

public class TVSet {
    private boolean m_isOn = false;
    private int m_selectedChannel = 1;
    private int m_previousChannel = 1;

    public boolean isTurnedOn()
    {
        return m_isOn;
    }
    public void turnOn()
    {
        m_isOn = true;
    }
    public void turnOff()
    {
        m_isOn = false;
    }
    public int getChannel()
    {
        return m_isOn ? m_selectedChannel : 0;
    }
    public boolean selectChannel(int channel)
    {
        m_previousChannel = m_selectedChannel;
        boolean isAvailableChannel = (channel >= 1) && (channel <= 99);
        if (isAvailableChannel && m_isOn)
        {
            m_selectedChannel = channel;
            return true;
        }
        return false;
    }
    public boolean selectPreviousChannel()
    {
        if (m_isOn)
        {
            int tmp = m_selectedChannel;
            m_selectedChannel = m_previousChannel;
            m_previousChannel = tmp;
            return true;
        }
        return false;
    }

}
