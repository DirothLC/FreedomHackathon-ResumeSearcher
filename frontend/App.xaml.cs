using System;
using System.Net.Http;
using System.Windows;

namespace Frontend
{
    public partial class App : Application
    {
        public static readonly HttpClient HttpClient = new HttpClient
        {
            BaseAddress = new Uri("192.168.0.110:8080")
        };

        protected override void OnStartup(StartupEventArgs e)
        {
            base.OnStartup(e);
        }
    }
}
