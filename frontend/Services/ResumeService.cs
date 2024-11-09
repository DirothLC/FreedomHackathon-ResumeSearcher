using System.Net.Http;
using System.Text.Json;
using Frontend.Models;

namespace Frontend.Services
{
    public class ResumeService
    {
        private readonly HttpClient _httpClient;

        public ResumeService()
        {
            _httpClient = new HttpClient { BaseAddress = new Uri("http://localhost:8080") };
        }

        public async Task<List<Resume>> GetResumesAsync()
        {
            HttpResponseMessage response = await _httpClient.GetAsync("/api/resumes");

            if (response.IsSuccessStatusCode)
            {
                var json = await response.Content.ReadAsStringAsync();
                return JsonSerializer.Deserialize<List<Resume>>(json);
            }
            else
            {
                throw new Exception($"Ошибка при получении данных: {response.ReasonPhrase}");
            }
        }
    }
}
